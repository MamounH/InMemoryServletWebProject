<%@include file="../../common/header.jspf"%>
<%@include file="../../common/NavigationBar/AdminNavigation.jspf"%>

<style> .table-wrapper {
    max-height: 450px;
    overflow: scroll;
}
</style>



<div class="container">

    <div class="table-wrapper">
    <table class = "table table-striped table-hover table-condensed">

        <thead>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Role</th>
        </thead>
        <tbody>
        <tr>
            <c:forEach items="${list}" var="user">
            <td>${user.fName}</td>
            <td>${user.lName}</td>
            <td>${user.username}</td>
            <td>${user.role}</td>
                <td><a class="btn btn-info" href="/Admin/UpdateUser?email=${user.username}">Update User</a></td>
                <td><a class="btn btn-danger" href="/Admin/DeleteUser?username=${user.username}"
                       onclick="if (!(confirm('are you sure you want to delete this student'))) return false"
                >Delete User</a></td>



        </tr>
        </c:forEach>

        </tbody>
    </table>
    </div>
    <a class="btn btn-success" href="/Admin/AddUser">Add New User</a>

</div>





<%@include file="../../common/footer.jspf"%>
