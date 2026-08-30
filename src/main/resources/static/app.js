/**
 * API CONFIGURATION
 */
const API_BASE_URL = "http://localhost:8080/api";

const ENDPOINTS = {
    // PermitAll Endpoints
    LOGIN: `${API_BASE_URL}/no-auth/login`,
    REGISTER: `${API_BASE_URL}/no-auth/register`,
    VERSION: `${API_BASE_URL}/no-auth/version`,
    
    // Authenticated Endpoints
    GET_BALANCE: `${API_BASE_URL}/auth/account/balance`,
    DEPOSIT: `${API_BASE_URL}/auth/account/deposit`,
    WITHDRAW: `${API_BASE_URL}/auth/account/withdraw`,
    TRANSACTIONS: `${API_BASE_URL}/auth/account/transactions`
};

/**
 * SINGLE PAGE APPLICATION (SPA) CONTROLLER
 */
class ATMApp {
    constructor() {
        this.token = localStorage.getItem("jwt_token") || null;
        this.username = localStorage.getItem("username") || null;
        this.init();
    }

    init() {
        this.bindEvents();
        if (this.token) {
            this.showAuthenticatedState();
        } else {
            this.navigateTo("view-login");
        }
    }

    navigateTo(viewId) {
        document.querySelectorAll(".view").forEach(v => v.classList.add("hidden"));
        const targetView = document.getElementById(viewId);
        if (targetView) targetView.classList.remove("hidden");

        if (viewId === "view-dashboard") {
            this.fetchBalance();
        }
    }

    bindEvents() {
        const loginForm = document.getElementById("login-form");
        if (loginForm) loginForm.addEventListener("submit", (e) => this.handleLogin(e));
        
        const depositForm = document.getElementById("deposit-form");
        if (depositForm) depositForm.addEventListener("submit", (e) => this.handleDeposit(e));
        
        const withdrawForm = document.getElementById("withdraw-form");
        if (withdrawForm) withdrawForm.addEventListener("submit", (e) => this.handleWithdraw(e));
        
        const logoutBtn = document.getElementById("logout-btn");
        if (logoutBtn) logoutBtn.addEventListener("click", () => this.handleLogout());
    }

    // Generic HTTP Fetch Yardımcısı
    async authFetch(url, options = {}) {
        const headers = {
            "Content-Type": "application/json",
            ...(this.token && { "Authorization": `Bearer ${this.token}` }),
            ...options.headers
        };

        const response = await fetch(url, { ...options, headers });

        if (response.status === 401 || response.status === 403) {
            this.handleLogout();
            throw new Error("Oturum süresi doldu. Lütfen tekrar giriş yapın.");
        }

        return response;
    }

    // 1. KULLANICI GİRİŞİ (WebController @PostMapping("/login") ile tam uyumlu)
    async handleLogin(e) {
        e.preventDefault();
        const usernameInput = document.getElementById("username").value.trim();
        const passwordInput = document.getElementById("password").value.trim();

        try {
            const response = await fetch(ENDPOINTS.LOGIN, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ username: usernameInput, password: passwordInput })
            });

            const result = await response.json();

            // ApiResponse<AuthServiceResponse> yanıt yapısı kontrolü
            if (response.ok && result.success !== false && result.data && result.data.token) {
                this.token = result.data.token;
                this.username = usernameInput;
                
                localStorage.setItem("jwt_token", this.token);
                localStorage.setItem("username", this.username);
                
                this.showAuthenticatedState();
                this.showToast(result.message || "Giriş başarılı", "success");
            } else {
                this.showToast(result.message || "Giriş başarısız!", "error");
            }
        } catch (err) {
            console.error("Login Hatası:", err);
            this.showToast("Sunucuya bağlanılamadı.", "error");
        }
    }

    // 2. BAKİYE GETİRME (AuthanticatedController @GetMapping("/account/balance") ile tam uyumlu)
    async fetchBalance() {
        try {
            const response = await this.authFetch(ENDPOINTS.GET_BALANCE, { method: "GET" });
            const result = await response.json();

            // ApiResponse<BigDecimal> yanıtı doğrudan result.data içinde numeric değer döndürür
            if (response.ok && result.data !== undefined && result.data !== null) {
                const numericBalance = Number(result.data);
                const balanceDisplay = document.getElementById("account-balance");
                if (balanceDisplay) {
                    balanceDisplay.innerText = `₺${numericBalance.toFixed(2)}`;
                }
            } else {
                this.showToast(result.message || "Bakiye bilgisi alınamadı.", "error");
            }
        } catch (err) {
            console.error("Bakiye çekme hatası:", err);
            if (err.message !== "Oturum süresi doldu. Lütfen tekrar giriş yapın.") {
                this.showToast("Bakiye bilgisi yüklenirken bir hata oluştu.", "error");
            }
        }
    }

    // 3. PARA YATIRMA
    async handleDeposit(e) {
        e.preventDefault();
        const amountInput = document.getElementById("deposit-amount").value;
        const amount = parseFloat(amountInput);

        if (isNaN(amount) || amount <= 0) {
            this.showToast("Geçerli bir tutar giriniz.", "error");
            return;
        }

        try {
            const response = await this.authFetch(ENDPOINTS.DEPOSIT, {
                method: "POST",
                body: JSON.stringify({ amount })
            });

            const result = await response.json();

            if (response.ok && result.success !== false) {
                this.showToast(result.message || "Para yatırma başarılı", "success");
                this.navigateTo("view-dashboard");
            } else {
                this.showToast(result.message || "İşlem başarısız.", "error");
            }
        } catch (err) {
            console.error("Deposit Hatası:", err);
            this.showToast("Bağlantı hatası.", "error");
        }
    }

    // 4. PARA ÇEKME
    async handleWithdraw(e) {
        e.preventDefault();
        const amountInput = document.getElementById("withdraw-amount").value;
        const amount = parseFloat(amountInput);

        if (isNaN(amount) || amount <= 0) {
            this.showToast("Geçerli bir tutar giriniz.", "error");
            return;
        }

        try {
            const response = await this.authFetch(ENDPOINTS.WITHDRAW, {
                method: "POST",
                body: JSON.stringify({ amount })
            });

            const result = await response.json();

            if (response.ok && result.success !== false) {
                this.showToast(result.message || "Para çekme başarılı", "success");
                this.navigateTo("view-dashboard");
            } else {
                this.showToast(result.message || "Bakiye yetersiz veya geçersiz tutar.", "error");
            }
        } catch (err) {
            console.error("Withdraw Hatası:", err);
            this.showToast("Bağlantı hatası.", "error");
        }
    }

    showAuthenticatedState() {
        const userInfo = document.getElementById("user-info");
        const userNameDisplay = document.getElementById("user-name-display");
        
        if (userInfo) userInfo.classList.remove("hidden");
        if (userNameDisplay) userNameDisplay.innerText = this.username;
        
        this.navigateTo("view-dashboard");
    }

    handleLogout() {
        this.token = null;
        this.username = null;
        localStorage.removeItem("jwt_token");
        localStorage.removeItem("username");
        
        const userInfo = document.getElementById("user-info");
        if (userInfo) userInfo.classList.add("hidden");
        
        this.navigateTo("view-login");
        this.showToast("Çıkış yapıldı.", "success");
    }

    showToast(message, type = "error") {
        const toast = document.getElementById("toast-message");
        if (toast) {
            toast.innerText = message;
            toast.className = `toast ${type}`;
            toast.classList.remove("hidden");
            setTimeout(() => toast.classList.add("hidden"), 3000);
        }
    }
}

// Uygulamayı Başlat
document.addEventListener("DOMContentLoaded", () => {
    window.app = new ATMApp();
});