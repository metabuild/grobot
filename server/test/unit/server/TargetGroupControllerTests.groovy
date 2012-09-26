package server



import org.junit.*
import grails.test.mixin.*

@TestFor(TargetGroupController)
@Mock(TargetGroup)
class TargetGroupControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/nodeGroup/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.nodeGroupInstanceList.size() == 0
        assert model.nodeGroupInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.nodeGroupInstance != null
    }

    void testSave() {
        controller.save()

        assert model.nodeGroupInstance != null
        assert view == '/nodeGroup/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/nodeGroup/show/1'
        assert controller.flash.message != null
        assert TargetGroup.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/nodeGroup/list'

        populateValidParams(params)
        def nodeGroup = new TargetGroup(params)

        assert nodeGroup.save() != null

        params.id = nodeGroup.id

        def model = controller.show()

        assert model.nodeGroupInstance == nodeGroup
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/nodeGroup/list'

        populateValidParams(params)
        def nodeGroup = new TargetGroup(params)

        assert nodeGroup.save() != null

        params.id = nodeGroup.id

        def model = controller.edit()

        assert model.nodeGroupInstance == nodeGroup
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/nodeGroup/list'

        response.reset()

        populateValidParams(params)
        def nodeGroup = new TargetGroup(params)

        assert nodeGroup.save() != null

        // test invalid parameters in update
        params.id = nodeGroup.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/nodeGroup/edit"
        assert model.nodeGroupInstance != null

        nodeGroup.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/nodeGroup/show/$nodeGroup.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        nodeGroup.clearErrors()

        populateValidParams(params)
        params.id = nodeGroup.id
        params.version = -1
        controller.update()

        assert view == "/nodeGroup/edit"
        assert model.nodeGroupInstance != null
        assert model.nodeGroupInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/nodeGroup/list'

        response.reset()

        populateValidParams(params)
        def nodeGroup = new TargetGroup(params)

        assert nodeGroup.save() != null
        assert TargetGroup.count() == 1

        params.id = nodeGroup.id

        controller.delete()

        assert TargetGroup.count() == 0
        assert TargetGroup.get(nodeGroup.id) == null
        assert response.redirectedUrl == '/nodeGroup/list'
    }
}
