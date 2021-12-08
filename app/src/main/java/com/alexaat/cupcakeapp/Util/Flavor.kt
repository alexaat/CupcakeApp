package com.alexaat.cupcakeapp.Util

enum class Flavor{
    VANILLA {
        override fun toString():String {
            return "Vanilla"
        }
    },
    CHOCOLATE {
        override fun toString():String {
            return "Chocolate"
        }
    },
    RED_VELVET {
        override fun toString():String {
            return "Red Velvet"
        }
    },
    SALTED_CARAMEL {
        override fun toString():String {
            return "Salted Caramel"
        }
    },
    COFFEE {
        override fun toString(): String {
            return "Coffee"
        }
    }
}
