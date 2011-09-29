;; Copyright (c) 2011 Michael S. Klishin
;;
;; The use and distribution terms for this software are covered by the
;; Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.html at the root of this distribution.
;; By using this software in any fashion, you are agreeing to be bound by
;; the terms of this license.
;; You must not remove this notice, or any other, from this software.

(ns langohr.exchange
  (:refer-clojure :exclude [declare])
  (:import (com.rabbitmq.client Channel AMQP$Exchange$DeclareOk AMQP$Exchange$DeleteOk AMQP$Exchange$BindOk)))

;;
;; API
;;

(defn declare
  "Declares an exchange using exchange.declare AMQP method"
  (^AMQP$Exchange$DeclareOk [^Channel channel ^String name ^String type]
     (.exchangeDeclare channel name type))
  (^AMQP$Exchange$DeclareOk [^Channel channel ^String name ^String type &{ :keys [durable auto-delete internal arguments] :or {durable false, auto-delete false, internal false} }]
     (.exchangeDeclare channel name type durable auto-delete internal arguments)))


(defn delete
  "Deletes an exchange using exchange.delete AMQP method"
  (^AMQP$Exchange$DeleteOk [^Channel channel ^String name]
     (.exchangeDelete channel name))
  (^AMQP$Exchange$DeleteOk [^Channel channel ^String name if-unused]
     (.exchangeDelete channel name if-unused)))


(defn bind
  "Binds a queue to an exchange using exchange.bind AMQP method (a RabbitMQ-specific extension)"
  (^AMQP$Exchange$BindOk [^Channel channel ^String destination ^String source]
     (.exchangeBind channel destination source ""))
  (^AMQP$Exchange$BindOk [^Channel channel ^String destination ^String source &{ :keys [routing-key arguments] :or { routing-key "", arguments nil } }]
     (.exchangeBind channel destination source routing-key arguments)))
