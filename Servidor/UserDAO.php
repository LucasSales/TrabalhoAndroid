<?php
/**
 * Created by PhpStorm.
 * User: loopback
 * Date: 19/01/16
 * Time: 23:41
 */

include_once 'User.php';

class UserDAO{

    private $conexao;

    public function __construct($conexao){
        $this->conexao = $conexao;
    }

    public function cadastrar(User $user){

        try {
            $sql = "insert into usuario(registro,nick) values (?,?)";

            $a = $this->conexao->prepare($sql);

            $a->bindParam(1, $user->getRegistroId());
            $a->bindParam(2, $user->getNick());


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

    public function buscarPorNick(User $user){
        try{
            $sql = "select *from usuario where nick = ?";

            $a = $this->conexao->prepare($sql);

            $a->bindParam(1,$user->getNick());

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

    public function buscaNickPorId($id){
        try{
            $sql = "select *from usuario where id = ?";

            $a = $this->conexao->prepare($sql);

            $a->bindParam(1,$id);

            if(!$a->execute())
                return false;


          return $a->fetchObject();


        }catch(Exception $e){
            return false;
        }
    }

    public function buscaPorToken($token){
        try{
            $sql = "select *from usuario where registro = ?";

            $a = $this->conexao->prepare($sql);

            $a->bindParam(1,$token);

            if(!$a->execute())
                return false;


            return $a->fetchObject();


        }catch(Exception $e){
            return false;
        }
    }
}