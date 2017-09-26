<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Categorias</title>
</head>
<body>
	<form name="frmCategoria" id="frmCategoria" action="ControleCategoria" method="post">
	  <div id="esquerda">
	    <p>
	      <label for="txtName">Nome: <span class="obrigatorio">*</span> <span class="dicaCampo">(Exemplo: Combustivel)</span></label>
	      <br />
	      <input type="text" name="txtName" id="txtName" maxlength="100" size="50" class="required" value="${param['txtName']}"/>
	    </p>
	    <p>
	      <select name="comboRaiz">  
			  <c:forEach  items="${listCategoria}" var="elemento">  
			    <option value="${elemento}">${elemento.name}</option>  
			  </c:forEach>  
		  </select>
	    </p>
	  </div>
	  <div class="divisor"></div>
	  <input type="submit" name="btnSalvar" id="btnSalvar" value=" Salvar " class="botaoCadastrar" />
	  <input type="submit" name="btnCancelar" id="btnCancelar" value=" Cancelar " class="botaoCancelar" />
	</form>
</body>
</html>