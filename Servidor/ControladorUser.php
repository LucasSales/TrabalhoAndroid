<?php
/**
 * Created by PhpStorm.
 * User: loopback
 * Date: 19/01/16
 * Time: 23:47
 */

include_once 'UserDAO.php';
include_once 'Conexao.php';

class ControladorUser{

    private $dao;

    public function __construct(){
        $this->dao = new UserDAO(Conexao::getConexao());
    }

    public function cadastrar($registro_id,$nick){
        $user = new User();
        $user->setRegistroId($registro_id);
        $user->setNick($nick);
        return $this->dao->cadastrar($user);
    }

    public function buscar($registro_id){
        $user = new User();
        $user->setId($registro_id);
        return $this->dao->buscarUsuarioToken($user);
    }
    public function buscarNick($nick){
        $user = new User();
        $user->setNick($nick);
        return $this->dao->buscarPorNick($user);
    }

    public static function getInstance(){
        return new ControladorUser();
    }

    public function buscaNickPorId($id){
        return $this->dao->buscaNickPorId($id);
    }
    public function buscarPorToken($token){
        return $this->dao->buscaPorToken($token);
    }
}