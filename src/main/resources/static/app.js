// Hakkımızda kutusunu açıp kapatan fonksiyon
function toggleAbout() {
    const aboutModal = document.getElementById("about-modal");
    if (aboutModal.style.display === "block") {
        aboutModal.style.display = "none";
    } else {
        aboutModal.style.display = "block";
    }
}

// Login sayfasına yönlendiren fonksiyon
function goToLogin() {
    window.location.href = "/login";
}

// Form gönderildiğinde çalışan temsili fonksiyon
function handleLogin(event) {
    event.preventDefault();
    const username = document.getElementById("username").value;
    alert(`Hoş geldiniz, ${username}! Giriş başarılı.`);
}