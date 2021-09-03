<%@include file="../common/header.jspf"%>
<%@include file="../common/NavigationBar/EditiorNav.jspf"%>

<div class="container">


    <form method="POST" action="/Editor/AddQuote">


        <p class="text-warning" color="red">${errorM}</p>

        <fieldset class="form-group">
            <label>Quote</label>
            <input name="quote" type="text" class="form-control" required/> <BR/>

        </fieldset>

        <fieldset class="form-group">

        <label>Select Book</label>
        <select name="bookId">
            <c:forEach items="${list}" var="book">
                <option value="${book.value.ID}"> ${book.value.name} , ${book.value.author} </option>
            </c:forEach>
        </select>
        </fieldset>

        <a class="btn btn-success" href="/Editor/EQuotes">Cancel Adding</a>
        <input  class="btn btn-success" name="add" type="submit" />


    </form>



</div>

<%@include file="../common/footer.jspf"%>
