<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Seven Wonders - Pick One</title>
  <style>
    body{font-family: Arial, sans-serif; padding: 20px;}
    .card{display:inline-block; margin:20px; text-align:center;}
    .card img{width:320px; height:220px; object-fit:cover; border-radius:8px; cursor:pointer;}
  </style>
</head>
<body>
  <h1>Explore (2) Wonders</h1>
  <div class="card">
    <a href="${pageContext.request.contextPath}/wonder?id=1">
      <img src="images/taj_mahal.jpg" alt="Taj Mahal"/>
    </a>
    <div>Taj Mahal</div>
  </div>
  <div class="card">
    <a href="${pageContext.request.contextPath}/wonder?id=2">
      <img src="images/great_wall.jpg" alt="Great Wall"/>
    </a>
    <div>Great Wall of China</div>
  </div>
</body>
</html>
