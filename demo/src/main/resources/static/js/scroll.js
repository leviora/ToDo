document.addEventListener("DOMContentLoaded", function () {
    const links = document.querySelectorAll(".nav-link");

    links.forEach(link => {
        link.addEventListener("click", function (event) {
            const href = this.getAttribute("href");

            if (href.startsWith("#")) {
                event.preventDefault();

                const targetId = href.substring(1);
                const targetSection = document.getElementById(targetId);

                if (targetSection) {
                    window.scrollTo({
                        top: targetSection.offsetTop - 50,
                        behavior: "smooth"
                    });

                    history.pushState(null, "", "/" + targetId);
                }
            } else {

                window.location.href = href;
            }
        });
    });

    const path = window.location.pathname.substring(1);
    const section = document.getElementById(path);
    if (section) {
        setTimeout(() => {
            window.scrollTo({
                top: section.offsetTop - 50,
                behavior: "smooth"
            });
        }, 100);
    }
});
