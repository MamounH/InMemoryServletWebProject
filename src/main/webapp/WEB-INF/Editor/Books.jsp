<%@include file="../common/header.jspf"%>
<%@include file="../common/NavigationBar/EditiorNav.jspf"%>

<style> .table-wrapper {
    max-height: 450px;
    overflow: scroll;
}
</style>



<div class="container">

    <div class="table-wrapper">

        <table class = "table table-striped table-hover table-condensed">


            <thead>
            <th>Book ID</th>
            <th>Book Name</th>
            <th>Author</th>
            <th>Subject</th>
            <th>Publisher</th>
            <th>Year</th>
            </thead>
            <tbody>
            <tr>
                <c:forEach items="${list}" var="book">
                <td>${book.value.ID}</td>
                <td>${book.value.name}</td>
                <td>${book.value.author}</td>
                <td>${book.value.subject}</td>
                <td>${book.value.publisher}</td>
                <td>${book.value.year}</td>
                    <td><a class="btn btn-info" href="/Editor/UpdateBook?ID=${book.value.ID}">Update Book</a></td>
                <td><a class="btn btn-danger" href="/Editor/DeleteBook?ID=${book.value.ID}"
                       onclick="if (!(confirm('are you sure you want to delete this book'))) return false"
                >Delete Book</a></td>



            </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
    <a class="btn btn-success" href="/Editor/AddBook">Add New Book</a>

</div>





<%@include file="../common/footer.jspf"%>
