$(function(){
    appendTask = function(data){
        var taskCode = '<a href="#" class="task-link" data-id="' + data.id + '">' + data.name + '</a><a href="#" class="task-del" data-id="' + data.id + '">X</a><br>';
        $('#task-list').append('<div class="task">' + taskCode + '</div>');
    };
   /* appendTaskResp = function(data){
        var taskDate = '<a href="#" class="task-link" data-id="' + data.id + '">' + data.name + ' (выполнить до ' + data.date + ')</a> <a href="#" class="task-del" data-id="' + data.id + '">X</a><br>';
        $('#task-list').append('<div class="task">' + taskDate + '</div>');
    };*/

    function loadTasks() {
        $.get('/tasks/', function (response) {
            $('#task-list').html('');
            for (i in response) {
                appendTask(response[i]);
            }
        });
    }

//Loading tasks on load page
    loadTasks();

    //Show adding task form
    $('#show-add-task-form').click(function(){
        $('#todo-form').css('display', 'flex');
    });

    //Show tasks
    $('#show-tasks').click(function(){
        loadTasks();
    });

    //Closing adding task form
    $('#todo-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

    //Getting task
    $(document).on('click', '.task-link', function(){
        var link = $(this);
        var taskId = link.data('id');
        $.ajax({
            method: "GET",
            url: '/task/' + taskId,
            success: function(response){
                var code = '<span>Ответственный:<i><a href="#" class="task-resp" data-id="' + taskId + '">' + response.responsible + '</a></i></span>';
                link.parent().append(code);
            },
            error: function(response){
                if(response.status == 404) {
                    alert('Задача не найдена!');
                }
            }
        });
        return false;
    });

    //Getting tasks for responsible
    $(document).on('click', '.task-resp', function(){
        var link = $(this);
        var taskId = link.data('id');
        $.ajax({
            method: "GET",
            url: '/tasks/responsible/' + taskId,
            success: function(response){
                $('#task-list').html('<div>Задачи для сотрудника: <b>' + response[0].responsible + '</b></div>');
                for(i in response) {
                    appendTaskResp(response[i]);
                }
            },
            error: function(response){
                if(response.status == 404) {
                    alert('Задача не найдена!');
                }
            }
        });
        return false;
    });
    //Remove task
    $(document).on('click', '.task-del', function(){
        var link = $(this);
        var taskId = link.data('id');
        $.ajax({
            method: "DELETE",
            url: '/task/' + taskId,
            success: function(response){
              link.parent().remove();
            },
            error: function(response){
                if(response.status == 404) {
                    alert('Задача не найдена!');
                }
            }
        });
        return false;
    });

    //Adding task
    $('#save-task').click(function(){
        var data = $('#todo-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/tasks/',
            data: data,
            success: function(response){
                $('#todo-form').css('display', 'none');
                var task = {};
                task.id = response;
                var dataArray = $('#todo-form form').serializeArray();
                for(i in dataArray) {
                    task[dataArray[i]['name']] = dataArray[i]['value'];
                }
                appendTask(task);
            }
        });
        return false;
    });
});