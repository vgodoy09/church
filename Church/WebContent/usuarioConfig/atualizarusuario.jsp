<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Atualizar Usuario</title>

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
                    <h1 class="page-header">Atualizar Usuario</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Atualizar Usuario
                        </div>
                        <div class="panel-body">
                            <div class="row">
                             	<form role="form" name="form" action="ControleUsuarioConfig" method="post">
                                	<div class="col-lg-6">
                                        <div class="form-group">
                                            <label>Nome</label>
                                            <input class="form-control" placeholder="Nome" name="nome" value="${usuar.nome}" autofocus>
                                            <p class="help-block">Coloque o Nome do Usuario Exemplo: João da Silva.</p>
                                        </div>
                                        <div class="form-group">
                                            <label>Endereço</label>
                                            <input class="form-control" placeholder="Endereco" name="endereco" value="${usuar.endereco}">
                                            <p class="help-block">Coloque o Endereço Exemplo: Rua Manoel Martins Sanches.</p>
                                        </div>
                                        <div class="form-group">
                                            <label>Nº</label>
                                            <input class="form-control" placeholder="numero" name="numero" value="${usuar.numero}" >
                                            <p class="help-block">Coloque  numero da casa Exemplo: 41.</p>
                                        </div>
                                        <div class="form-group">
                                            <label>Data de Nascimento</label>
                                            <input class="form-control" placeholder="data" name="dataFormatada" value="${usuar.dataFormatada}" >
                                            <p class="help-block">Coloque a Data de Nascimento Exemplo: 19/10/1991.</p>
                                        </div>
                                        <div class="form-group">
                                            <label>Sexo</label>
                                            <select name="sexo" class="form-control">
                                            	<option  selected></option>
                                            	<option  value="${usuar.sexo}">Femenino</option>
                                            	<option  value="${usuar.sexo}">Masculino</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>Login</label>
                                            <input class="form-control" name="email" value="${login}" disabled>
                                        </div>
	                                </div>
	                                <!-- /.col-lg-6 (nested) -->
	                                <div class="col-lg-6">
	                                	<div class="form-group">
                                            <label>Status</label>
                                            <select name="status" class="form-control">
                                            	<option  selected></option>
                                            	<option  value="${usuar.status}">Ativo</option>
                                            	<option  value="${usuar.status}">Inativo</option>
                                            </select>
                                        </div>
	                                	<div class="form-group">
                                            <label>Pais</label>
                                            <select name="paises" class="form-control">
                                            	<option  selected></option>
                                            	<c:forEach items="${listarPais" var="pais">
	                                                <option value="${pais.id}">${pais.name}</option>
                                            	</c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>Estado</label>
                                            <select name="estados" class="form-control">
                                            	<option  selected></option>
                                            	<c:forEach items="${listarEstado" var="estado">
	                                                <option value="${estado.id}">${estado.name}</option>
                                            	</c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>Cidade</label>
                                            <select name="cidades" class="form-control">
                                            	<option  selected></option>
                                            	<c:forEach items="${listarCidade" var="cidade">
	                                                <option value="${cidade.id}">${cidade.name}</option>
                                            	</c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>Senha</label>
                                            <input class="form-control" type="password" placeholder="Digite sua Senha..." name="password" value="${senha}" autofocus>
                                        </div>
	                                    <div class="form-group text-right" style="margin-top:0px;">
		                                	<input type="submit" class="btn btn-default" name="btnCadastro" value="Cadastrar" />
		                                </div>
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