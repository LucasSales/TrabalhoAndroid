<?php
/**
 * Created by PhpStorm.
 * User: lucas
 * Date: 27/01/16
 * Time: 03:17
 */
include_once 'Conexao.php';
include_once 'MensagemDAO.php';
include_once 'LocalDAO.php';
class ControladorMensagem{
    private $daoMsg;
    private $daoLoc;

    public function __construct(){
        $this->daoMsg = new MensagemDAO(Conexao::getConexao());
        $this->daoLoc = new LocalDAO(Conexao::getConexao());
    }

    public function cadastrar($token,$idTo,$mensagem, $latitude, $longitude){
        $msg = new Mensagem();
        $local = new Local();

        $local->setLatitude($latitude);
        $local->setLongitude($longitude);

        $msg->setIdTo($idTo);
        $msg->setToken($token);
        $msg->setMensagem($mensagem);

        $id = $this->daoLoc->cadastrar($local);

        if($id == false)
            return false;

        $msg->setIdLoc($id);
        $valor = 0;
        return $this->daoMsg->cadastrar($msg,$valor);
    }

    public function buscar(){
        return $this->daoMsg->buscar();
    }
    public function buscaMsgLocal($ids,$id){
        return $this->daoMsg->buscaMsgLocalizacao($ids,$id);
    }
    public function buscaMsgIdsLoc($ids,$id){
        return $this->daoMsg->buscaMsgIdsLoc($ids,$id);
    }

    public function vista($idTo){
        return $this->daoMsg->visto($idTo);
    }


    public static function getInstance(){
        return new ControladorMensagem();
    }
}