<%@include file="../common/header.jspf"%>
<%@include file="../common/NavigationBar/EditiorNav.jspf"%>

<div class="container">

    <form method="POST" action="/Editor/UpdateBook">

        <input type="hidden" name="id" value="${book.ID}"/>

        <fieldset class="form-group">
            <label>Book Name</label>
            <input name="name" type="text" class="form-control" value="${book.name}" required/> <BR/>

        </fieldset>

        <fieldset class="form-group">
            <label>Book Author</label>
            <input name="author" type="text" class="form-control" value="${book.author}" required/> <BR/>

        </fieldset>

        <fieldset class="form-group">
            <label>Book Subject</label>
            <input name="subject" type="text" class="form-control" value="${book.subject}" required/> <BR/>

        </fieldset>

        <fieldset class="form-group">
            <label>Book Publisher</label>
            <input name="publisher" type="text" class="form-control" value="${book.publisher}" required/> <BR/>
        </fieldset>

        <fieldset class="form-group">
            <label>Book Publication Year</label>
            <input name="year" type="text" class="form-control" value="${book.year}" required/> <BR/>
        </fieldset>




        <a class="btn btn-success" href="/Editor/Books">Cancel Updating</a>
        <input  class="btn btn-success" name="add" type="submit" />


    </form>



</div>

<%@include file="../common/footer.jspf"%>
