<?php
/**
 * Created by PhpStorm.
 * User: lucas
 * Date: 28/01/16
 * Time: 01:58
 */
include_once 'Conexao.php';
include_once 'Amigo.php';

class AmigoDAO{
    private $conexao;

    public function __construct($conexao){
        $this->conexao = $conexao;
    }

    public function cadastrar(Amigo $amigo){

        try {
            $sql = "insert into amigo(id,idAmigo,nick) values (?,?,?)";

            $a = $this->conexao->prepare($sql);

            $a->bindParam(1, $amigo->getId());
            $a->bindParam(2, $amigo->getIdAmigo());
            $a->bindParam(3, $amigo->getNickname());


            return $a->execute();

        }catch (Exception $e) {
            return false;
        }
    }

    public function buscarUsuarioToken(User $user){
        try{
            $sql = "select *from usuario where id = ?";

            $a = $this->conexao->prepare($sql);

            $a->bindParam(1,$user->getId());

            if(!$a->execute())
                return false;

            $arrayalunos = array();

            while($aluno = $a->fetchObject())
                array_push($arrayalunos,$aluno);

            return $arrayalunos;

        }catch(Exception $e){
            return false;
        }
    }
}