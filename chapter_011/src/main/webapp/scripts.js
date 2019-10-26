function createTable() {
    $.ajax("./json", {
        complete: function (data) {
            var result = "";
            var users = JSON.parse(data.responseText);
            for (var i = 0; i < users.length; i++) {
                result += "<tr><td>" + users[i].name + "</td>"
                    + "<td>" + users[i].surname + "</td>"
                    + "<td>" + users[i].gender + "</td>"
                    + "<td>" + users[i].description + "</td></tr>";
            }
            var table = document.getElementById("body");
            table.innerHTML = result;
        }
    })
}
function validate() {
    var result = true;
    var name = $('#name');
    var lastName = $('#surname');
    if (name.val() === '') {
        alert(name.attr('title'));
        result = false;
    }
    if (lastName.val() === '') {
        alert(lastName.attr('title'));
        result = false;
    }
    return result;
}
function addRow() {
    var name = $('#name').val();
    var surname = $('#surname').val();
    var gender = document.querySelector('input[name="gender"]:checked').value;
    var desc = $('#comment').val();
    $('#table tr:last')
        .after('<tr><td>' + name +'</td><td>' + surname +'</td><td>' + gender +'</td><td>' + desc +'</td></tr>');
}

function addUser() {
    if (validate()) {
        var data = JSON.stringify({
                "name": $("#name").val(),
                "surname": $("#surname").val(),
                "gender": document.querySelector('input[name="gender"]:checked').value,
                "description": $("#comment").val()
        });
        $.ajax("./json", {
            method: 'POST',
            data: data,
            complete: function f() {
                console.log("SUCCESS : ");
                addRow();
            }
        });
        createTable();
    }
}