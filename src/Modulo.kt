class Modulo {
    var alumnos = arrayOfNulls<Alumno>(15)
    var evaluaciones = Array(alumnos.size) { arrayOfNulls<Float>(4) }

    fun establecerNota(idAlumno: String, ev: String, nota: Float): Boolean {
        var comprobar = false
        var indiceAl = alumnos.indexOfFirst { (it?.id ?: "").equals(idAlumno) }

        if (ev in PRIMERA_EVALUACION..CUARTA_EVALUACION || indiceAl != -1) {
            comprobar = true
            evaluaciones[ev.toInt()][indiceAl] = nota
        }
        return comprobar
    }

    fun calculaEvaluacionFinal() {
        for (i in alumnos.indices) {
            var notas = 0.0
            for (j in 0..2) {
                notas += (evaluaciones[i][j]) ?: 0.toFloat()
            }
            evaluaciones[i][3] = (notas / 3.0).toFloat()
        }
    }

    fun listaNotas(ev: String) {
        if (ev in PRIMERA_EVALUACION..CUARTA_EVALUACION) {
            for (i in 0..alumnos.size) {

            }
        }
    }

    fun numeroAprobados(ev: String): Int {
        var numAprobados = 0
        if (ev in PRIMERA_EVALUACION..CUARTA_EVALUACION) {
            for (i in alumnos.indices) {
                if ((evaluaciones[ev.toInt()][i] ?: 0).toFloat() >= 5.0) {
                    numAprobados += 1
                }
            }
        }
        return numAprobados
    }

    fun notaMasBaja(ev: String): Float {
        var notaMasBaja = 0.0F
        if (ev in PRIMERA_EVALUACION..CUARTA_EVALUACION) {
            notaMasBaja = evaluaciones[ev.toInt()].maxOf { (it ?: 0).toFloat() }
        }
        return notaMasBaja
    }

    fun notaMasAlta(ev: String): Float {
        var notaMasAlta = 0.0F
        if (ev in PRIMERA_EVALUACION..CUARTA_EVALUACION) {
            notaMasAlta = evaluaciones[ev.toInt()].maxOf { (it ?: 0).toFloat() }
        }
        return notaMasAlta
    }

    fun notaMedia(ev: String): Float {
        var media = 0.0F
        if (ev in PRIMERA_EVALUACION..CUARTA_EVALUACION) {
            for (i in alumnos.indices) {
                media = media + (evaluaciones[ev.toInt()][i] ?: 0).toFloat()
            }
        }
        return media
    }

    fun hayAlumnosConDiez(ev: String): Boolean {
        var comprobar = false
        if (ev in PRIMERA_EVALUACION..CUARTA_EVALUACION) {
            comprobar = evaluaciones[ev.toInt()].filter { (it ?: 9) == 10 }.isNotEmpty()
        }
        return comprobar
    }

    fun hayAlumnosAprobados(ev: String): Boolean {
        var comprobar = false
        if (ev in PRIMERA_EVALUACION..CUARTA_EVALUACION) {
            comprobar = evaluaciones[ev.toInt()].filter { (it ?: 4.0F) > 5.0F }.isNotEmpty()
        }
        return comprobar
    }

    fun primeraNotaNoAprobada(ev: String): Float {
        if (ev in PRIMERA_EVALUACION..CUARTA_EVALUACION) {
            val num = ev.toInt()
            return evaluaciones[num].filter { (it ?: -1.0F) < 5.0F }.maxOf { (it ?: 0).toFloat() }
        }
        return -1.0F
    }

    fun listarNotasOrdenadas(ev: String) {
        if (ev in PRIMERA_EVALUACION..CUARTA_EVALUACION) {
            val num = ev.toInt()
            if (num != 5) {
                for (i in 0..alumnos.size) {

                }
            }
        }
    }

    fun matricularAlumno(a: Alumno): Boolean {
        if (null in alumnos) {
            alumnos[alumnos.indexOfFirst { it == null }] = a
            return true
        } else {
            return false
        }
    }

    fun bajaAlumno(idAlumno: String): Boolean {
        var borrado = false
        var pos = alumnos.indexOfFirst { (it?.id ?: "").equals(idAlumno) }
        if (pos != -1) {
            alumnos[pos] = null
            borrado = true
        }
        return borrado
    }

    companion object {
        val PRIMERA_EVALUACION = "0"
        val SEGUNDA_EVALUACION = "1"
        val TERCERA_EVALUACION = "2"
        val CUARTA_EVALUACION = "3"
    }
}