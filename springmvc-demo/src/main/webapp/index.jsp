<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<body>
<h2>Hello World!</h2>
<div>
    <h2> 文件上传</h2>
    <fieldset>
        <%--
        1 method="post"
        2 enctype="multipart/form-data"
        3 <input type="file" name="uploadName"/>
        --%>
        <form method="post" enctype="multipart/form-data" action="/demo/upload">
            <input type="file" name="uploadName"/>
            <input type="submit" value="上传"/>
        </form>
    </fieldset>
</div>
</body>
</html>
