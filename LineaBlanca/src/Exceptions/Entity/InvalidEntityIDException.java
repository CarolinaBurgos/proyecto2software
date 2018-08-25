/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions.Entity;

/**
 *
 * @author Toshiba
 */
public class InvalidEntityIDException extends Exception{
    public InvalidEntityIDException(String message) {
        super(message);
    }
    
}
