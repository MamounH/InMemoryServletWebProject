<%@include file="../common/header.jspf"%>
<%@include file="../common/NavigationBar/ViewerNav.jspf"%>

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
            <th>Quotes</th>
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
                <td><a class="btn btn-info" href="/Viewer/BookQuotes?ID=${book.value.ID}">Quotes</a></td>

            </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>

</div>





<%@include file="../common/footer.jspf"%>
