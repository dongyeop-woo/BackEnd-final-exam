package com.book.book.service;

import com.book.book.dto.UserRegisterRequestDTO;
import com.book.book.entity.Book;
import com.book.book.entity.User;
import com.book.book.exception.BookNotFoundException;
import com.book.book.exception.LoginFailException;
import com.book.book.exception.UserNotFoundException;
import com.book.book.mapper.UserMapper;
import com.book.book.repository.UserRepository;
import com.book.book.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final StoreRepository storeRepository;

    // 회원가입, passwordEncoder 는 mapper 에서 제외하고 서비스 로직에서 처리함
    @Override
    @Transactional
    public User register(UserRegisterRequestDTO userRegisterRequestDTO) {
        String encodePassword = passwordEncoder.encode(userRegisterRequestDTO.getPassword());
        User newUser = userMapper.toEntity(userRegisterRequestDTO).toBuilder()
                .password(encodePassword)
                .build();
        return userRepository.save(newUser);
    }

    // 로그인 검증
    @Override
    @Transactional(readOnly = true)
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(LoginFailException::new);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new LoginFailException();
        }

        return user;
    }

    // 유저 이름 표시
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);

        return userMapper.toSecurityUser(user);
    }

    // 찜 목록 불러오기
    @Override
    @Transactional(readOnly = true)
    public List<Book> getFavorites(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
        return user.getFavorites() == null ? List.of() : List.copyOf(user.getFavorites());
    }

    // 찜 넣기
    @Override
    @Transactional
    public void toggleFavorite(String username, Long bookId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
        Book book = storeRepository.findById(bookId)
                .orElseThrow(BookNotFoundException::new);

        if (user.getFavorites() == null) {
            user.setFavorites(new java.util.HashSet<>());
        }
        if (user.getFavorites().contains(book)) {
            user.getFavorites().remove(book);
        } else {
            user.getFavorites().add(book);
        }

        userRepository.save(user);
    }
}
