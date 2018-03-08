import com.tw.neo4j.crud.{Connection, User}
import org.scalatest.FunSuite

class CRUDTest extends FunSuite{
  test("insert operation using neo4j driver"){
    val session = Connection.getSession()
    val user = User("Tanmay", "Goel", 26, "Pune")
    //val insertScript = s"CREATE (user:Users {name:'${user.name}',last_name:'${user.last_name}',age:${user.age},city:'${user.city}'})"
    //session.run(insertScript)

    val nameOfFriends = "\"" + List(new User("Ta")).mkString("\", \"") + "\""
    val script = s"MATCH (user:Users {name: 'Tairman'}) FOREACH (name in [${nameOfFriends}] | CREATE (user)-[:FRIENDS]->(:Users {name:name}))"
    session.run(script)

    val readScript = s"MATCH (user:Users) RETURN user"
    val result = session.run(readScript)

    val records = result.list()
    println("Found: " + records.size())

    records.forEach(r => println(r.asMap))
  }
}
