package com.tw.neo4j.crud

import org.neo4j.driver.v1.{AuthTokens, GraphDatabase, Session}

object Connection {
  def getSession(): Session = {
    val driver = GraphDatabase.driver("bolt://localhost/7687", AuthTokens.basic("neo4j", "Neo4j"))
    driver.session
  }
}
