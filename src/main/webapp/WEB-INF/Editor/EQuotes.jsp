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
            <th>Quote ID</th>
            <th>Book Id</th>
            <th>Book Name</th>
            <th>Quote</th>
            </thead>
            <tbody>
            <tr>
                <c:forEach items="${list}" var="quote">
                <td>${quote.value.id}</td>
                <td>${quote.value.bookId}</td>
                <td>${quote.value.bookName}</td>
                <td>${quote.value.quote}</td>
                <td><a class="btn btn-info" href="/Editor/UpdateQuote?ID=${quote.value.id}">Update Quote</a></td>
                <td><a class="btn btn-danger" href="/Editor/DeleteQuote?ID=${quote.value.id}"
                       onclick="if (!(confirm('are you sure you want to delete this quote'))) return false"
                >Delete Quote</a></td>



            </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
    <a class="btn btn-success" href="/Editor/AddQuote">Add New Quote</a>

</div>





<%@include file="../common/footer.jspf"%>
