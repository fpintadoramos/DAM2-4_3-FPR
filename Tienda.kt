data class Tienda(val nombre: String, val clientes: List<Clientes>) {
    fun obtenerConjuntoDeClientes(): List<Clientes> = clientes

    //fun obtenerCiudadesDeClientes(): Set<Ciudad> = setOf<Ciudad>()

    fun obtenerClientesPor(ciudad:Ciudad): List<Clientes> = clientes.filter { it.ciudad == ciudad }

    fun checkTodosClientesSonDe(ciudad : Ciudad): Boolean = clientes.all { it.ciudad == ciudad }

    fun hayClientesDe(ciudad: Ciudad): Boolean = clientes.any { it.ciudad == ciudad }

    fun cuentaClientesDe(ciudad: Ciudad): Int = clientes.count{it.ciudad == ciudad}

    fun encuentraClienteDe(ciudad: Ciudad): Clientes? = clientes.find { it.ciudad == ciudad }

    fun obtenerClientesOrdenadosPorPedidos(): List<Clientes> = clientes.sortedByDescending { it.pedidos.size }

    fun obtenerClientesConPedidosSinEngregar(): Set<Clientes?> = setOf(clientes.find { it.pedidos.count { it.estaEntregado==false }>it.pedidos.count{it.estaEntregado==true}})

    //fun obtenerProductosPedidos(): List<Producto> = listOf()

    //fun obtenerProductosPedidos(): Set<Producto> = setOf()

    //fun encuentraProductoMasCaro(): Producto? =

    fun obtenerNumeroVecesProductoPedido(producto: Producto): Int {
        var num = 0

        clientes.forEach {
            it.pedidos.forEach {
                var num = it.productos.count { it == producto}
            }
        }

        return num
    }

    fun agrupaClientesPorCiudad(): MutableMap<Ciudad, List<Clientes?>>{
        var mapa = mutableMapOf<Ciudad, List<Clientes?>>()
        var ciudades = listOf<Ciudad>()
        clientes.forEach {
            if (it.ciudad in ciudades) else ciudades += it.ciudad
        }

        clientes.forEach {
            ciudades.forEach { c -> mapa[c] = listOf<Clientes?>(clientes.find {it.ciudad == c }) }
        }

        return mapa
    }

    fun dineroGastado(): Double {
        var sum = 0.0
        clientes.forEach { it.pedidos.forEach { it.productos.forEach { sum += it.precio } } }
        return sum
    }
}

data class Clientes(val nombre: String, val ciudad: Ciudad, val pedidos: List<Pedido>) {
    override fun toString() = "$nombre from ${ciudad.nombre}"
}

data class Pedido(val productos: List<Producto>, val estaEntregado: Boolean)

data class Producto(val nombre: String, val precio: Double) {
    override fun toString() = "'$nombre' for $precio"
}

data class Ciudad(val nombre: String) {
    override fun toString() = nombre
}