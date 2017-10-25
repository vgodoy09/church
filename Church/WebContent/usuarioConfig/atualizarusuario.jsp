<%@page import="br.com.church.dao.DAOCidade"%>
<%@page import="br.com.church.facade.FacadeCidade"%>
<%@page import="br.com.church.util.CheckInstanceObject"%>
<%@page import="br.com.church.dao.DAOEstado"%>
<%@page import="br.com.church.facade.FacadeEstado"%>
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
    <link href="/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    
    <!-- 1º Adicionamos o arquivo CSS do plugin ao código. -->
    <!-- Datepicker -->
    <link href="dist/css/datepicker.css" rel="stylesheet">
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
                                		<input type="hidden" name="txtid" value="${usuar.id_usuario}" />
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
											<div class='input-group date datepicker'>
												<input type='text' name="dataFormatada" class="form-control" value="${usuar.dataFormatada}" />
												<span class="input-group-addon">
													<span class="glyphicon glyphicon-calendar"></span>
												</span>
											</div>
                                            <p class="help-block">Coloque a Data de Nascimento Exemplo: 19/10/1991.</p>
                                        </div>
                                        <div class="form-group">
                                            <label>Sexo</label>
                                            <select name="sexo" class="form-control">
                                            	<option  value="${usuar.sexo}" selected>${usuar.sexo}</option>
                                            	<option  value="${usuar.sexo}">FEMININO</option>
                                            	<option  value="${usuar.sexo}">MASCULINO</option>
                                            </select>
                                            <p class="help-block">Escolha o sexo Exemplo: Masculino.</p>
                                        </div>
                                        <div class="form-group">
                                            <label>Status</label>
                                            <select name="status" class="form-control">
                                            	<option  value="${usuar.status}" selected>${usuar.status}</option>
                                            	<option  value="${usuar.status}">ATIVO</option>
                                            	<option  value="${usuar.status}">INATIVO</option>
                                            </select>
                                            <p class="help-block">Status da pessoa Exemplo: Ativo.</p>
                                        </div>
	                                </div>
	                                <!-- /.col-lg-6 (nested) -->
	                                <div class="col-lg-6">
	                                	<div class="form-group">
                                            <label>Pais</label>
	                                            <select name="paises" onchange="location.4href = 'ControleUsuarioConfig?paises';" class="form-control">
	                                            	<option value="${usuar.idPais}" selected>${usuar.nom_pais}</option>
	                                            	<c:forEach items="${listarPais}" var="pais">
		                                                <option value="${pais.id}" >${pais.nome}</option>
	                                            	</c:forEach>
	                                            </select>
                                            <p class="help-block">Escolha o pais Exemplo: Brasil.</p>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label>Estado</label>
                                            <select name="estados" onchange="submit()" class="form-control">
                                            	<option value="${usuar.idEstado}" selected>${usuar.nom_estado}</option>
                                            	<c:forEach items="${listarEstado}" var="estado">
	                                                <option value="${estado.id}" >${estado.nome}</option>
                                            	</c:forEach>
                                            </select>
                                            <p class="help-block">Escolha o estado Exemplo: Sao Paulo.</p>
                                        </div>
                                        <div class="form-group">
                                            <label>Cidade</label>
                                            <select name="cidades" class="form-control">
                                            	<option value="${usuar.idCidade}" selected>${usuar.nom_cidade}</option>
                                            	<c:forEach items="${listarCidade}" var="cidade">
	                                                <option value="${cidade.id}" >${cidade.nome}</option>
                                            	</c:forEach>
                                            </select>
                                            <p class="help-block">Escolha a Cidade Exemplo: Jacarei.</p>
                                        </div>
                                        <div class="form-group">
                                            <label>Login</label>
                                            <input class="form-control" name="emailVisual" value="${usuar.login}" disabled>
                                            <input type="hidden" name="email" value="${usuar.login}">
                                            <p class="help-block">Email Exemplo: victorpradodegodoy09@gmail.com.</p>
                                        </div>
                                        <div class="form-group">
                                            <label>Senha</label>
                                            <input class="form-control" type="password" placeholder="Digite sua Senha..." name="password" value="${usuar.senha}" autofocus>
                                        </div>
                                        <p class="help-block">Coloque a senha Exemplo: ika3b9.</p>
	                                    <div class="form-group text-right" style="margin-top:0px;">
		                                	<input type="submit" class="btn btn-default" name="btnAtualizar" value="Atualizar" />
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
    
    <!-- Referência do arquivo JS do plugin após carregar o jquery -->
      <!-- Datepicker -->
      <script src="dist/js/bootstrap-datepicker.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>
    <script>
	    $('.datepicker').datepicker({
		  format: 'dd/mm/yyyy',  
	    });
    </script>
	<script>
		function doSomething() {
			var comboEstados = document.getElementById("estados");
		    while (comboEstados.length) {
		        comboEstados.remove(0);
		    }
		    var comboCidades = document.getElementById("cidades");
		    while (comboCidades.length) {
		        comboCidades.remove(0);
		    }
		}
	</script>  
	<script>
		function doSomethingeState() {
			var comboCidades = document.getElementById("cidades");
		    while (comboCidades.length) {
		        comboCidades.remove(0);
		    }
		}
	</script>  
</body>
</html>