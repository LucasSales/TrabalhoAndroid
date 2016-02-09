<?php

include_once 'ControladorMensagem.php';

if(isset($_POST['jsonObject'])){
    $jsonObject = json_decode($_POST['jsonObject']);

    $token = $jsonObject->message->idFrom;
    $idTo = $jsonObject->message->idTo;
    $latitude = $jsonObject->message->local->latitude;
    $longitude = $jsonObject->message->local->longitude;
    $mensagem = $jsonObject->message->message;

    //echo "asdasd ".$jsonObject;
    $controlador = ControladorMensagem::getInstance();


    //echo json_encode(['resposta'=>$controlador->cadastrar(null,$mensagem,null,$latitude,$longitude)]);
    echo json_encode(array('mensagem'=>$controlador->cadastrar($token,$idTo,$mensagem,$latitude,$longitude)));
}