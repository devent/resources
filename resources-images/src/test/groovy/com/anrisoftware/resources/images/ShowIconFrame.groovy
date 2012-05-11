package com.anrisoftware.resources.images;

import static javax.swing.SwingUtilities.invokeLater

import java.awt.BorderLayout
import java.awt.GridLayout
import java.awt.event.ActionListener

import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel

class ShowIconFrame {

	def images

	long timeout = 60000

	def synchronized call() {
		invokeLater {
			def frame = new JFrame("Icon Show")
			frame.layout = new BorderLayout()

			def count = Math.ceil(getImages().size() / 2) as int
			def panel = new JPanel(new GridLayout(count, count, 4, 4))
			getImages().each {
				def label = new JLabel(new ImageIcon(it))
				panel.add label
			}

			def button = new JButton("Close")
			button.addActionListener({e ->
				frame.visible = false
				synchronized (owner.owner) {
					owner.owner.notifyAll()
				}
			}as ActionListener)

			frame.add panel, BorderLayout.CENTER
			frame.add button, BorderLayout.SOUTH

			frame.setSize 300, 300
			frame.defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
			frame.rootPane.defaultButton = button
			frame.visible = true
		}
		wait timeout
	}

	List getImages() {
		if (images instanceof List) {
			return images
		} else {
			images = [images]
			return images
		}
	}
}
