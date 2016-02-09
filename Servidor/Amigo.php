<?php
/**
 * Created by PhpStorm.
 * User: lucas
 * Date: 28/01/16
 * Time: 01:57
 */
class Amigo{
    public $id;
    public $idAmigo;
    public $nickname;

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
    public function getIdAmigo()
    {
        return $this->idAmigo;
    }

    /**
     * @param mixed $idAmigo
     */
    public function setIdAmigo($idAmigo)
    {
        $this->idAmigo = $idAmigo;
    }

    /**
     * @return mixed
     */
    public function getNickname()
    {
        return $this->nickname;
    }

    /**
     * @param mixed $nickname
     */
    public function setNickname($nickname)
    {
        $this->nickname = $nickname;
    }

}