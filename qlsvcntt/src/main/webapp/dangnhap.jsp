<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="nhom2.qlsv.HelloAppEngineTest" %>
<%@ page import="nhom2.qlsv.Csdldatastore" %>
<!DOCTYPE html>
<html>
<head>
    <title>dang nhap</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>

<h2>dang nhap</h2>

<form action="qlsv.jsp" >
  <div class="container">
    <label for="uname"><b>tai khoan</b></label>
    <input type="text" placeholder="Nhap tai khoan" name="uname" required>

    <label for="psw"><b>mat khau</b></label>
    <input type="password" placeholder="Nhap mat khau" name="psw" required>
        
    <button type="submit" name="button" value="button1">Button 1</button>
    <label>
      <input type="checkbox" checked="checked" name="remember"> nho tai khoan
    </label>
  </div>

  <div class="container" style="background-color:#f1f1f1">
    <button type="button" class="huy">huy</button>
    <span class="psw">quen <a href="#">mat khau</a></span>
  </div>
  
  <div class = "test">
  	<button type="button" class="test" > button2 <%= Csdldatastore.createEntity() %> </button>
  	<label id="label"> <%= HelloAppEngineTest.testhello() %> </label>
  </div>
</form>

</body>
</html>
