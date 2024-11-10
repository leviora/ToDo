document.addEventListener("DOMContentLoaded", () => {
    const menuButton = document.querySelector('.menu-button');
    const navMenu = document.querySelector('.nav-menu');
    const closeButton = document.querySelector('.close-button');
    const menuItems = document.querySelectorAll('.menu-item a');

    // Domyślnie ukryj przycisk zamknięcia
    closeButton.classList.add('hidden');

    // Otwieranie menu
    menuButton.addEventListener('click', () => {
        navMenu.classList.add('show');
        navMenu.classList.remove('hidden');
        menuButton.classList.add('hidden'); // Ukrywa ikonę trzech kresek
        closeButton.classList.remove('hidden'); // Wyświetla przycisk krzyżyka
        document.body.style.overflow = 'hidden';
    });

    // Zamykanie menu przyciskiem krzyżyka
    closeButton.addEventListener('click', () => {
        navMenu.classList.remove('show');
        menuButton.classList.remove('hidden'); // Przywraca ikonę trzech kresek
        closeButton.classList.add('hidden'); // Ukrywa przycisk krzyżyka
        document.body.style.overflow = '';
    });

    // Zamykanie menu po kliknięciu opcji
    menuItems.forEach(item => {
        item.addEventListener('click', () => {
            navMenu.classList.remove('show');
            menuButton.classList.remove('hidden'); // Przywraca ikonę trzech kresek
            closeButton.classList.add('hidden'); // Ukrywa przycisk krzyżyka
            document.body.style.overflow = '';
        });
    });

    // Zamykanie menu kliknięciem poza kafelkami
    navMenu.addEventListener('click', (event) => {
        if (event.target === navMenu) {
            navMenu.classList.remove('show');
            menuButton.classList.remove('hidden');
            closeButton.classList.add('hidden');
            document.body.style.overflow = '';
        }
    });


});
