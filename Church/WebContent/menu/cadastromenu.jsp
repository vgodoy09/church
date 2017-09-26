<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Cadastro Menu</title>

    <!-- Bootstrap Core CSS -->
    <link href="bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
	<jsp:include page="/menu/menu.jsp"></jsp:include>
    <div id="wrapper">
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Cadastrar Menus</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Cadastrar Menus
                        </div>
                        <div class="panel-body">
                            <div class="row">
                             	<form role="form" name="form" action="ControleMenu" method="post">
                                	<div class="col-lg-6">
                                        <div class="form-group">
                                            <label>Nome</label>
                                            <input class="form-control" placeholder="Nome" name="name" value="${param['name']}" autofocus>
                                            <p class="help-block">Coloque o Nome do Menu Exemplo: Listar Menu.</p>
                                        </div>
                                        <div class="form-group">
                                            <label>Imagem</label>
                                            <input class="form-control" placeholder="Imagem" name="image" value="${param['image']}" >
                                            <p class="help-block">Coloque o Caminho da Imagem Exemplo: /imagens/menu_imagem.jpg.</p>
                                        </div>
                                        <div class="form-group">
	                                        <label>Link</label>
	                                        <input class="form-control" placeholder="Link" name="link" value="${param['link']}" >
	                                        <p class="help-block">Coloque o Link do menu Exemplo: /ControleMenu.</p>
	                                    </div>
	                                    <div class="form-group">
                                            <label>Menu Pai</label>
                                            <select name="menus" class="form-control">
                                            	<option  selected></option>
                                            	<c:forEach items="${listarMen}" var="men">
	                                                <option value="${men.id}">${men.name}</option>
                                            	</c:forEach>
                                            </select>
                                        </div>
	                                     <div class="form-group text-right" style="margin-top:0px;">
		                                	 <input type="submit" class="btn btn-default" name="btnCadastro" value="Cadastrar" />
		                                 </div>                                        
<!--                                         <div class="form-group"> -->
<!--                                             <label>Ordem</label> -->
<!--                                             <select name="menus" class="form-control"> -->
<!--                                             	<option  selected></option> -->
<%--                                             	<c:forEach items="${listarOrden}" var="ord"> --%>
<%-- 	                                                <option value="${ord}">${ord}</option> --%>
<%--                                             	</c:forEach> --%>
<!--                                             </select> -->
<!--                                         </div> -->
	                                </div>
	                                <!-- /.col-lg-6 (nested) -->
	                                <div class="col-lg-6">
	                                </div>
                                <!-- /.col-lg-6 (nested) -->
                                </form>
                            </div>
                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>
</body>
</html>