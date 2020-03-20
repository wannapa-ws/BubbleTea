package com.example.fb_login

class BubbleTea(var name: String, var amount: Float) {

    companion object {
        fun getSampleBubbleTeaData(size: Int): ArrayList<BubbleTea> {
            val bubbletea: ArrayList<BubbleTea> = ArrayList()
            for (i in 0 until size) {
                bubbletea.add(BubbleTea("Type $i", Math.random().toFloat() * 100))
            }
            return bubbletea
        }
    }

}