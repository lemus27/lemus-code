/*
 * DO NOT EDIT.  THIS FILE IS GENERATED FROM
 * idl\nsIJSContextStack.idl
 */

package org.mozilla.interfaces;

public interface nsIJSContextStack extends nsISupports {

  String NS_IJSCONTEXTSTACK_IID =
    "{c67d8270-3189-11d3-9885-006008962422}"; //$NON-NLS-1$

  int getCount();

  long peek();

  long pop();

  void push(long cx);

}