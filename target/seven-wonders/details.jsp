<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>${requestScope.name}</title>
  <style>body{font-family:Arial, sans-serif; padding:20px;} img{max-width:600px; border-radius:6px;}</style>
</head>
<body>
  <a href="${pageContext.request.contextPath}/index.jsp">← Back</a>
  <h1>${requestScope.name}</h1>
  <img src="${requestScope.img}" alt="${requestScope.name}"/>
  <p style="max-width:800px;">${requestScope.desc}</p>
</body>
</html>
