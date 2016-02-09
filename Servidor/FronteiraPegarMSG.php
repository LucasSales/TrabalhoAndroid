<?php
/**
 * Created by PhpStorm.
 * User: lucas
 * Date: 29/01/16
 * Time: 04:22
 */
include_once 'ControleLocal.php';
include_once 'ControladorMensagem.php';
include_once 'ControladorGCM.php';
include_once 'ControladorUser.php';
if(isset($_POST['jsonObject'])) {
    $jsonObject = json_decode($_POST['jsonObject']);
    $latitude = $jsonObject->temMensagem->local->latitude;
    $longitude = $jsonObject->temMensagem->local->longitude;;
    $distancia = $jsonObject->temMensagem->local->distancia;
    $token = $jsonObject->temMensagem->token;

    $conUser= new ControladorUser();
    $conMsg = new ControladorMensagem();
    $conLoc = new ControleLocal();

    $id = $conUser->buscarPorToken($token);

    $a = $conLoc->buscar($latitude , $longitude , 3);

    $mensagem = $conMsg->buscaMsgLocal($a,$id->id);


        //$ids = $conMsg->buscaMsgIdsLoc($a,$id->id);

        //$boo = $conMsg->vista($ids);

        $ctrl = new ControladorGCM();


        echo json_encode($ctrl->enviarDadoParaTelefone([$token],["msg"=>$mensagem]));


}