package globalunit



import org.junit.*
import grails.test.mixin.*

@TestFor(VZFacebookController)
@Mock(VZFacebook)
class VZFacebookControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/VZFacebook/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.VZFacebookInstanceList.size() == 0
        assert model.VZFacebookInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.VZFacebookInstance != null
    }

    void testSave() {
        controller.save()

        assert model.VZFacebookInstance != null
        assert view == '/VZFacebook/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/VZFacebook/show/1'
        assert controller.flash.message != null
        assert VZFacebook.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/VZFacebook/list'


        populateValidParams(params)
        def VZFacebook = new VZFacebook(params)

        assert VZFacebook.save() != null

        params.id = VZFacebook.id

        def model = controller.show()

        assert model.VZFacebookInstance == VZFacebook
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/VZFacebook/list'


        populateValidParams(params)
        def VZFacebook = new VZFacebook(params)

        assert VZFacebook.save() != null

        params.id = VZFacebook.id

        def model = controller.edit()

        assert model.VZFacebookInstance == VZFacebook
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/VZFacebook/list'

        response.reset()


        populateValidParams(params)
        def VZFacebook = new VZFacebook(params)

        assert VZFacebook.save() != null

        // test invalid parameters in update
        params.id = VZFacebook.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/VZFacebook/edit"
        assert model.VZFacebookInstance != null

        VZFacebook.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/VZFacebook/show/$VZFacebook.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        VZFacebook.clearErrors()

        populateValidParams(params)
        params.id = VZFacebook.id
        params.version = -1
        controller.update()

        assert view == "/VZFacebook/edit"
        assert model.VZFacebookInstance != null
        assert model.VZFacebookInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/VZFacebook/list'

        response.reset()

        populateValidParams(params)
        def VZFacebook = new VZFacebook(params)

        assert VZFacebook.save() != null
        assert VZFacebook.count() == 1

        params.id = VZFacebook.id

        controller.delete()

        assert VZFacebook.count() == 0
        assert VZFacebook.get(VZFacebook.id) == null
        assert response.redirectedUrl == '/VZFacebook/list'
    }
}
