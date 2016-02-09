<?php  

	/*include_once 'ControladorGCM.php';

	$token = "efOZci3bz24:APA91bGp-jdqgEKR-06qHGjdM_lWnTNauS9vzwmiQYj-Cqt-mO61lArvDIq6PyTZpDDKpC8G9VU2AgL9HhqyjNNO2_1SUo7UVu2xM9Mvh4ZZX2hRMWq5Fq2YnnYV0cxmDwD2SEswGXd1";

    $ctrl = new ControladorGCM();
    $dados = [""];


	$ctrl->enviarDadoParaTelefone([$token],["msg"=>"msg servidor GCM"]);*/

	include_once 'ControleLocal.php';
    include_once 'ControladorMensagem.php';
    include_once 'ControladorGCM.php';
    include_once 'ControladorUser.php';

    $token = "cojZDDz1RQE:APA91bGn1b4AjFM7DCRKT-JWYi4gAJ7wdDzT2--WXW-6rVY1s_S3ahtH_TiqUEATv9AVR1mCRBHoUT_G8G8_hftowpWkqcSVYVgVYntf2QPOQfqJ5UxZRlrcx2l-EF_5WpZjA-pm8yQc";
    //$id = 8;

    $con3 = new ControladorUser();
    $con2 = new ControladorMensagem();
	$con = new ControleLocal();

    $id = $con3->buscarPorToken($token);

    $a = $con->buscar(-3.712060 , -38.574517 , 3);

	$mensagem = $con2->buscaMsgLocal($a,$id->id);

    //$boo = $con2->vista($id->id);


    $ctrl = new ControladorGCM();
    //var_dump($id->id);

    //$msg = array();
    //for($i = 0; $i < sizeof($mensagem); $i++){
        //array_push($msg,$mensagem[$i]->mensagem);
    //}

    //echo "msg ".$msg;

    $ctrl->enviarDadoParaTelefone([$token],["msg"=>$mensagem]);





