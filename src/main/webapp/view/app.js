var app = angular.module('app', []);

app.controller('ContactCtrl', ['$scope', 'ContactsService', function ($scope, ContactsService) {

    $scope.ph_numbr = /^\+?\d{10}$/;

    $scope.firstNameMessage = "<span class=\"red-text\" ng-if=\"checkEmpty(contact.firstName)\">First Name is required</span>";
    $scope.lastNameMessage = "<span class=\"red-text\" ng-if=\"checkEmpty(contact.lastName)\">Last Name is required</span>";
    $scope.phoneMessage = "<span class=\"red-text\" ng-if=\"checkEmpty(contact.phone)\">Phone number is required</span>";

    $scope.checkPhone = function (phone) {
        return $scope.checkEmpty(phone) || $scope.ph_numbr.test(phone);
    };

    $scope.checkEmpty = function (value) {
        return value == null || value === '';
    };

    $scope.checkContact = function (contact) {
        return $scope.checkEmpty(contact.firstName)
            || $scope.checkEmpty(contact.lastName)
            || $scope.checkEmpty(contact.phone)
            || !$scope.checkPhone(contact.phone);
    };

    $scope.initAllContacts = function () {
        ContactsService.getAllContacts().then(function success(response) {
            $scope.Contacts = response.data;
        });
    };

    $scope.initAllContacts();

    $scope.addContact = function () {
        ContactsService.addContact($scope.contact.firstName, $scope.contact.lastName, $scope.contact.phone)
            .then(
                function success(response) {
                    $scope.message = 'Contact Added';
                    $scope.errorMessage = '';
                    $scope.contact.firstName = '';
                    $scope.contact.lastName = '';
                    $scope.contact.phone = '';
                    $scope.initAllContacts();
                },
                function error(response) {
                    $scope.errorMessage = 'Error adding contact!';
                    $scope.message = ''
                });
    };

    $scope.updateContact = function (id, firstName, lastName, phoneNumber) {

        ContactsService.updateContact(id, firstName, lastName, phoneNumber)
            .then(
                function success(response) {
                    $scope.message = 'Contact Updated';
                    $scope.errorMessage = '';
                    $scope.initAllContacts();
                },
                function error(response) {
                    $scope.errorMessage = 'Error updating contact!';
                    $scope.message = ''
                });
    };

    $scope.deleteContact = function (id) {
        ContactsService.deleteContact(id)
            .then(
                function success(response) {
                    $scope.message = 'Contact Deleted';
                    $scope.errorMessage = '';
                    $scope.initAllContacts();
                },
                function error(response) {
                    $scope.errorMessage = 'Error deleting contact!';
                    $scope.message = ''
                });
    }
}]);

app.service('ContactsService', ['$http', function ($http) {

    this.addContact = function addContact(firstName, lastName, phone) {
        return $http({
            method: 'POST',
            url: 'api/contact_book/contact',
            data: {firstName: firstName, lastName: lastName, phone: phone}
        });
    };

    this.updateContact = function updateContact(id, firstName, lastName, phone) {
        return $http({
            method: 'PUT',
            url: 'api/contact_book/contact',
            data: {id: id, firstName: firstName, lastName: lastName, phone: phone}
        });
    };

    this.deleteContact = function deleteContact(id) {
        return $http({
            method: 'DELETE',
            url: 'api/contact_book/contact/' + id
        })
    };

    this.getAllContacts = function getAllContacts() {
        return $http({
            method: 'GET',
            url: 'api/contact_book/contacts'
        });
    }


}]);