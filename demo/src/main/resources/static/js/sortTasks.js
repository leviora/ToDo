
document.addEventListener("DOMContentLoaded", function() {
    const taskListItems = document.querySelectorAll("li");
    taskListItems.forEach((item, index) => {
        setTimeout(() => {
            item.style.opacity = 1;
            item.style.transition = "opacity 0.5s ease-in";
        }, index * 100);
    });
});

