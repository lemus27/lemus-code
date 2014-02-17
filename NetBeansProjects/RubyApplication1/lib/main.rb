# To change this template, choose Tools | Templates
# and open the template in the editor.

require 'java'

JFrame = javax.swing.JFrame
JComponent = javax.swing.JComponent
GradientPaint = java.awt.GradientPaint
Color = java.awt.Color

class CustomComponent < JComponent
    @paint = GradientPaint.new(0, 0, Color::BLACK, 0, 200, Color::WHITE)

    def initialize
    end

    def paintComponent(g)
        g.paint = @paint
        g.fillRect(0, 0, getWidth, getHeight)
    end
end

frame = JFrame.new 'Test Frame'
frame.setSize(200, 200)

frame.add CustomComponent.new

frame.defaultCloseOperation = JFrame::EXIT_ON_CLOSE
frame.visible = true