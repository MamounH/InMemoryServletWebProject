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



            </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>

</div>





<%@include file="../common/footer.jspf"%>
