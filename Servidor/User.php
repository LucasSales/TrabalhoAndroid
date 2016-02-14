<?php
/**
 * Created by PhpStorm.
 * User: lucas
 * Date: 27/01/16
 * Time: 00:50
 */
class User{
    private $id;
    private $registro_id;
    private $nick;
    private $email;
    private $senha;
    private $urlFoto;

    /**
     * @return mixed
     */
    public function getUrlFoto()
    {
        return $this->urlFoto;
    }

    /**
     * @param mixed $urlFoto
     */
    public function setUrlFoto($urlFoto)
    {
        $this->urlFoto = $urlFoto;
    }



    /**
     * @return mixed
     */
    public function getNick()
    {
        return $this->nick;
    }

    /**
     * @param mixed $nick
     */
    public function setNick($nick)
    {
        $this->nick = $nick;
    }

    /**
     * @return mixed
     */
    public function getEmail()
    {
        return $this->email;
    }

    /**
     * @param mixed $email
     */
    public function setEmail($email)
    {
        $this->email = $email;
    }

    /**
     * @return mixed
     */
    public function getSenha()
    {
        return $this->senha;
    }

    /**
     * @param mixed $senha
     */
    public function setSenha($senha)
    {
        $this->senha = $senha;
    }

    /**
     * @return mixed
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @param mixed $id
     */
    public function setId($id)
    {
        $this->id = $id;
    }

    /**
     * @return mixed
     */
    public function getRegistroId()
    {
        return $this->registro_id;
    }

    /**
     * @param mixed $registro_id
     */
    public function setRegistroId($registro_id)
    {
        $this->registro_id = $registro_id;
    }

}