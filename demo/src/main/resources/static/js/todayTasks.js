document.addEventListener("DOMContentLoaded", () => {
    const totalTasks = parseInt(document.getElementById("tasksToDoCounter").textContent);
    animateCounter("tasksToDoCounter", 0, totalTasks, 2000);
});

function animateCounter(id, start, end, duration) {
    const counter = document.getElementById(id);
    let current = start;
    const increment = (end - start) / (duration / 10);

    function updateCounter() {
        current += increment;
        if (current >= end) {
            counter.textContent = end;
        } else {
            counter.textContent = Math.floor(current);
            setTimeout(updateCounter, 10);
        }
    }

    updateCounter();
}
