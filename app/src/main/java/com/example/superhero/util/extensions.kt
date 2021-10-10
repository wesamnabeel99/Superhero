package com.example.superhero.util

import android.view.KeyEvent

fun KeyEvent?.isKeyPressed() = this?.keyCode != null && this.keyCode == KeyEvent.KEYCODE_ENTER