# XmasModel
Helping out Santa Claus by creating a persistence layer to manage a gift list for the children of the earth.

Implemented using Spring and MyBatis. Consisting of the following business objects:

Child (id: int, name: String; hasOne: Address, hasMany: Gifts)
Address (id: int, text: String)
Gift (id: int, description: String)

ChildrenDAO:
void addChild(Child child);
void removeChild(Child child);
void updateChild(Child child);
Child getChildById(int id);
Child getAllChildren();
void addGift(Child child, Gift gift);
void removeGift(Child child, Gift gift);

AddressDAO:
void addAddress(Address address);
void removeAddress(Address address);
void updateAddress(Address address);
Child getAddressById(int id);
Child getAllAddresses();
