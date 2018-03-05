package sample.kotlin.intellij.backend


import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.SchemaUtils.drop

// MySQL imports
import com.mysql.*
import com.mysql.management.MysqldResource
import com.mysql.management.driverlaunched.MysqldResourceNotFoundException
import com.mysql.management.driverlaunched.ServerLauncherSocketFactory
import com.mysql.management.util.Files
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.TransactionManager
import java.sql.ResultSet


open class Database() {

    val dbHost = "localhost"
    val dbPort = "3306"
    val dbName = "testdb"
    val dbUrl = "jdbc:mysql://$dbHost:$dbPort"
    val dbUser = "root"
    val dbPassword = ""
    val dbDriver = "com.mysql.jdbc.Driver"
    val dbCharset = "utf8"
    val dbCollate = "utf8_general_ci"


    object Users : Table() {
        val id = varchar("id", 10).primaryKey() // Column<String>
        val name = varchar("name", length = 50) // Column<String>
        val cityId = (integer("city_id") references Cities.id).nullable() // Column<Int?>
    }

    object Cities : Table() {
        val id = integer("id").autoIncrement().primaryKey() // Column<Int>
        val name = varchar("name", 50) // Column<String>
    }

    fun execRawQuery(query: String) : Unit {
        TransactionManager.current().exec(query)
    }

    open fun create() {

        val conn = Database.connect(
                url = dbUrl,
                user = dbUser,
                password = dbPassword,
                driver = dbDriver
        )

        transaction {
            val dropSchemaQuery = "DROP DATABASE IF EXISTS $dbName"
            execRawQuery(dropSchemaQuery)

            val createSchemaQuery = "CREATE DATABASE $dbName CHARACTER SET $dbCharset COLLATE $dbCollate"
            execRawQuery(createSchemaQuery)

            val useSchemaQuery = "USE $dbName"
            execRawQuery(useSchemaQuery)

            create(Cities, Users)

            val saintPetersburgId = Cities.insert {
                it[name] = "St. Petersburg"
            } get Cities.id

            val munichId = Cities.insert {
                it[name] = "Munich"
            } get Cities.id

            Cities.insert {
                it[name] = "Prague"
            }

            Users.insert {
                it[id] = "andrey"
                it[name] = "Andrey"
                it[cityId] = saintPetersburgId
            }

            Users.insert {
                it[id] = "sergey"
                it[name] = "Sergey"
                it[cityId] = munichId
            }

            Users.insert {
                it[id] = "eugene"
                it[name] = "Eugene"
                it[cityId] = munichId
            }

            Users.insert {
                it[id] = "alex"
                it[name] = "Alex"
                it[cityId] = null
            }

            Users.insert {
                it[id] = "smth"
                it[name] = "Something"
                it[cityId] = null
            }

            Users.update({ Users.id eq "alex" }) {
                it[name] = "Alexey"
            }

            Users.deleteWhere { Users.name like "%thing" }

            println("All cities:")

            for (city in Cities.selectAll()) {
                println("${city[Cities.id]}: ${city[Cities.name]}")
            }
        }
    }

}