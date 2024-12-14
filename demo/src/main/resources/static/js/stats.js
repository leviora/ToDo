//document.addEventListener("DOMContentLoaded", function () {
//    fetch('/statystyki/data')
//        .then(response => {
//            if (!response.ok) {
//                throw new Error('Błąd podczas pobierania statystyk');
//            }
//            return response.json();
//        })
//        .then(data => {
//            document.getElementById('total-tasks').textContent = data.totalTasks;
//            document.getElementById('completed-tasks').textContent = data.completedTasks;
//            document.getElementById('pending-tasks').textContent = data.pendingTasks;
//        })
//        .catch(error => {
//            console.error(error);
//            document.getElementById('stats-container').textContent = 'Nie udało się załadować statystyk.';
//        });
//});
