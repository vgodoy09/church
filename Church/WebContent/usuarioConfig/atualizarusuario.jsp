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
                             	<form role="form" name="form" action="ControleApplication" method="post">
                                	<div class="col-lg-6">
                                		<input type="hidden" name="txtid" value="${app.id}" />
                                        <div class="form-group">
                                            <label>Nome</label>
                                            <input class="form-control" placeholder="Nome" name="nome" value="${app.nome}" autofocus>
                                            <p class="help-block">Coloque o Nome da Aplicação Exemplo: Administração.</p>
                                        </div>
                                        <div class="form-group">
                                            <label>Id do Botão</label>
                                            <input class="form-control" placeholder="Nome" name="idButton" value="${app.namebutton}">
                                            <p class="help-block">Coloque o id do botão.</p>
                                        </div>
                                        <div class="form-group">
                                            <label>Imagem Avaliada</label>
                                            <input class="form-control" placeholder="Imagem" name="imageAvailable" value="${app.imageLogoAvailable}" >
                                            <p class="help-block">Coloque o Caminho da Imagem Exemplo: /imagens/aplicacao_imagem.jpg.</p>
                                        </div>
                                        <div class="form-group">
                                            <label>Imagem Não Avaliada</label>
                                            <input class="form-control" placeholder="Imagem" name="imageUnavailable" value="${app.imageLogoUnavailable}" >
                                            <p class="help-block">Coloque o Caminho da Imagem Exemplo: /imagens/aplicacao_imagem.jpg.</p>
                                        </div>
	                                </div>
	                                <!-- /.col-lg-6 (nested) -->
	                                <div class="col-lg-6">
	                                	<div class="form-group">
	                                         <label>Link</label>
	                                         <input class="form-control" placeholder="Link" name="link" value="${app.link}" >
	                                         <p class="help-block">Coloque o Link da Aplicacao Exemplo: /ControleAplicacao.</p>
	                                     </div>
	                                     <div class="form-group">
	                                         <div class="checkbox">
	                                       		 <label>
	                                                 <input name="visible" type="checkbox"  value="${app.visible}">Visivel
	                                             </label>
	                                         </div>
	                                     </div>
	                                     <div class="form-group">
	                                         <label>Text area</label>
	                                         <textarea class="form-control" name="message" rows="3" >${app.messageUserActivation}</textarea>
	                                         <p class="help-block">Uma descrição breve da aplicação.</p>
	                                     </div>
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

    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>
</body>
</html>