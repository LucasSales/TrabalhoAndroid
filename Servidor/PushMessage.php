<?php
/**
 * Created by PhpStorm.
 * User: viniciusthiengo
 * Date: 8/9/15
 * Time: 10:57 PM
 */

    class PushMessage {


        public $title;
        public $message;


        public function __construct($message=null)
        {
            $this->message = $message;
        }

        /**
         * @return null
         */
        public function getTitle()
        {
            return $this->title;
        }

        /**
         * @param null $title
         */
        public function setTitle($title)
        {
            $this->title = $title;
        }

        /**
         * @return null
         */
        public function getMessage()
        {
            return $this->message;
        }

        /**
         * @param null $message
         */
        public function setMessage($message)
        {
            $this->message = $message;
        }


    }