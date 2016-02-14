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
    //Lucas
    $token2 = "etFCDIifE6A:APA91bENw-fEqxk91K5g7pmIzgBkbl2yxKrReQyv_DGo9vddPjvmnuwY24x-7i7H3DR_7b8ZskTyE23nf44HmedyMnBksxCkFG6LS1v6OOC0-_BwiIKYpWFyoatiD5HiXQJoU8292n7V";
    //Robso
    $token = "cNuypBwP3Ww:APA91bHK6axzN3irGRYap2WgcNz91idAslzb2zDeZI6NZEzupcGNrJRNuYdWYEIDSrel9B9MD5gmc2q1NcahTxLJuXkmib7hgkR6LaKsR_sFWCwcw9pakz4XL9POapagA71_aLDrAHBW";
    //$id = 8;

    $con3 = new ControladorUser();
    $con2 = new ControladorMensagem();
	$con = new ControleLocal();
    //-4.968855, -39.018472 qx
    // fortaleza -3.712060 , -38.574517
    $id = $con3->buscarPorToken($token);
    $a = $con->buscar(-4.968855 , -39.018472 , 3);

	$mensagem = $con2->buscaMsgLocal($a,$id->id);

    //$boo = $con2->vista($id->id);


    $ctrl = new ControladorGCM();




    $ids = $con2->buscaMsgIdsLoc($a,$id->id);
    var_dump($ids);

    $boo = $con2->vista($ids);
    //var_dump($id->id);

    //$msg = array();
    //for($i = 0; $i < sizeof($mensagem); $i++){
        //array_push($msg,$mensagem[$i]->mensagem);
    //}

    //var_dump($mensagem);

    $ctrl->enviarDadoParaTelefone([$token],["msg"=>$mensagem]);





