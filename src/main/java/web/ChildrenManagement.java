package web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import java.util.Set;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import dao.AddressDAO;
import dao.ChildDAO;
import dao.GiftDAO;
import business.Address;
import business.Child;
import business.Gift;

@Controller
public class ChildrenManagement {

    @Autowired
    private ChildDAO childDAO;

    @Autowired
    private AddressDAO addressDAO;

    @Autowired
    private GiftDAO giftDAO;

    @RequestMapping(value = "/childrenManagement", method = RequestMethod.GET)
    public void managementView(Model model) {
        model.addAttribute("childArray", childDAO.getAll());
    }

    @RequestMapping(value = "/childrenManagement", method = RequestMethod.POST)
    public String showDetailPage(WebRequest webRequest, Model model) {
        String command = webRequest.getParameter("cmd");

        if (command.contentEquals("show-detail-page")) {
            int id = Integer.parseInt(webRequest.getParameter("childId"));
            model.addAttribute("childId", id);

            Child child = childDAO.getById(id);
            model.addAttribute("name", child.getName());

            Set<Gift> gifts = child.getGifts();
            model.addAttribute("giftArray", gifts);
            model.addAttribute("giftId", webRequest.getParameter("giftId"));

            int addressID = child.getAddress().getId();
            Address address = addressDAO.getById(addressID);
            model.addAttribute("address", address.getAddress());
            model.addAttribute("addressId", address.getId());

            return "childDetail";
        } else if (command.contentEquals("add-child")) {
            String childName = webRequest.getParameter("childName");
            String address = webRequest.getParameter("address");
            if (childName.length() > 0 && address.length() > 0) {
                Address newAddress = new Address();
                newAddress.setAddress(address);
                addressDAO.saveOrUpdate(newAddress);

                Child newChild = new Child();
                newChild.setName(childName);
                newChild.setAddress(newAddress);

                childDAO.saveOrUpdate(newChild);
            }
            model.addAttribute("childArray", childDAO.getAll());
            return "childrenManagement";
        } else if (command.contentEquals("delete-child")) {
            int id = Integer.parseInt(webRequest.getParameter("childId"));
            if (id > 0) {
                Child deleteChild = childDAO.getById(id);
                childDAO.delete(deleteChild);
                model.addAttribute("childArray", childDAO.getAll());
                return "childrenManagement";
            }
        } else if (command.contentEquals("gift-overview")) {
            model.addAttribute("childArray", childDAO.getAll());
            return "giftOverview";
        }
        return "childrenManagement";
    }

    @RequestMapping(value = "/childDetail", method = RequestMethod.POST)
    public String updateChild(WebRequest webRequest, Model model, HttpServletRequest req) {
        String command = webRequest.getParameter("cmd");

        String childId = webRequest.getParameter("childId");
        int id = Integer.parseInt(childId);
        Child child = childDAO.getById(id);
        String newAddress = webRequest.getParameter("address");
        String name = webRequest.getParameter("name");
        int addressId = Integer.parseInt(webRequest.getParameter("addressId"));
        Set<Gift> gifts = child.getGifts();

        if (command.contentEquals("do-update-child")) {
            child.setName(name);
            childDAO.saveOrUpdate(child);
        } else if (command.contentEquals("do-update-address")) {
            if (newAddress.length() > 0 && addressId > 0) {
                Address updateAddress = addressDAO.getById(addressId);
                updateAddress.setAddress(newAddress);
                addressDAO.saveOrUpdate(updateAddress);
            }
        } else if (command.contentEquals("show-detail-page-gift")) {
            int giftId = Integer.parseInt(webRequest.getParameter("giftId"));
            Gift gift = giftDAO.getById(giftId);

            model.addAttribute("giftId", giftId);
            model.addAttribute("description", gift.getDescription());

            return "giftDetail";
        } else if (command.contentEquals("add-gift")) {
            String newDescription = webRequest.getParameter("giftDescription");
            Gift newGift = new Gift();
            newGift.setDescription(newDescription);

            child.addGift(newGift);
            childDAO.saveOrUpdate(child);
        } else if (command.contentEquals("delete-gift")) {
            int giftId = Integer.parseInt(webRequest.getParameter("giftId"));
            Gift gift = giftDAO.getById(giftId);

            child.removeGift(gift);
            childDAO.saveOrUpdate(child);
            giftDAO.delete(gift);
        } else if (command.contentEquals("childrenManagement")) {
            model.addAttribute("childArray", childDAO.getAll());
            return "childrenManagement";
        }
        model.addAttribute("childId", childId);
        model.addAttribute("name", name);
        model.addAttribute("addressId", addressId);
        model.addAttribute("address", newAddress);
        model.addAttribute("giftArray", gifts);

        model.addAttribute("childArray", childDAO.getAll());
        return "childDetail";
    }

    @RequestMapping(value = "/giftDetail", method = RequestMethod.POST)
    public String updateGift(WebRequest webRequest, Model model, HttpServletRequest req) {
        String command = webRequest.getParameter("cmd");

        if (command.contentEquals("do-update-gift")) {
            int giftId = Integer.parseInt(webRequest.getParameter("giftId"));
            Gift gift = giftDAO.getById(giftId);
            String newDescription = webRequest.getParameter("description");
            gift.setDescription(newDescription);

            giftDAO.saveOrUpdate(gift);
        }

        model.addAttribute("childArray", childDAO.getAll());
        return "childrenManagement";
    }

}