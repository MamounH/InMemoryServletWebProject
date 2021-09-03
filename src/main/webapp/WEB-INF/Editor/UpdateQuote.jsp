<%@include file="../common/header.jspf"%>
<%@include file="../common/NavigationBar/EditiorNav.jspf"%>

<div class="container">

    <form method="POST" action="/Editor/UpdateQuote">

        <input type="hidden" name="id" value="${quote.id}"/>

        <fieldset class="form-group">
            <label>Update Quote</label>
            <input name="quote" type="text" class="form-control" value="${quote.quote}" required/> <BR/>

        </fieldset>

        <fieldset class="form-group">
            <label>Update Book ID</label>
            <input name="bookId" type="text" class="form-control" value="${quote.bookId}" required/> <BR/>

        </fieldset>



        <a class="btn btn-success" href="/Editor/EQuotes">Cancel Updating</a>
        <input  class="btn btn-success" name="add" type="submit" />


    </form>



</div>

<%@include file="../common/footer.jspf"%>
